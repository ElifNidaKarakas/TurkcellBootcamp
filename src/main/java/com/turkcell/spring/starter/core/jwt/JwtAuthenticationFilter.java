package com.turkcell.spring.starter.core.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component  //spring ios içerisine bağımlılığı ekleyebilmek için
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {  //OncePerRequestFilter=> gelen her isteği filtreleyen istekten önce araya girip beli fonk.çalıştıran
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, //gelen istek
                                    @NonNull HttpServletResponse response,//gönderilecek response
                                    @NonNull FilterChain filterChain)//filtreler arasında ki ilişki ilgili diğer filtreye geçişi sağlayan yapı
            throws ServletException, IOException {
        // JWT'ye ulaşmak => request'in headeri
        final String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            // kullanıcı auth header göndermedi ya da geçersiz bir header gönderdi
            filterChain.doFilter(request,response);
            return;

            //header kısmı boşsa veya bearer boşluk seklinde devam etmiyorsa diğer filtreye geçiş yap
        }
        final String jwt = authHeader.substring(7); //bearer  kısmını atlar sadece token alır.
        // jwt içerisini kontrol etmek => jwt'yi decode/encode işlemleri
                final String username = jwtService.extractUsername(jwt);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UserDetails user = userDetailsService.loadUserByUsername(username);
                    // gelen jwtnin süresinin geçmemiş olması..
                    if(jwtService.isTokenValid(jwt, user)){
                        // Giriş işlemleri
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }

                filterChain.doFilter(request,response);
            }
}