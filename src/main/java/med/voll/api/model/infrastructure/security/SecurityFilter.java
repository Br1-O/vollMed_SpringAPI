package med.voll.api.model.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.model.repositories.IUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    //dependency injection
        private JWT_tokenService jwt_tokenService;
        private IUserRepository userRepository;
        SecurityFilter(JWT_tokenService jwt_tokenService, IUserRepository userRepository){
            this.jwt_tokenService = jwt_tokenService;
            this.userRepository = userRepository;
        }

    //security filter to ckeck valid user in JWT
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            var authHeader = request.getHeader("Authorization");

            if(authHeader != null){
                var token=authHeader.replace("Bearer ", "");

                System.out.println("Filter is being implemented!");
                System.out.println(token);
                System.out.println(jwt_tokenService.getSubject(token));

                var subject = jwt_tokenService.getSubject(token);

                if(subject!=null){
                    //entonces el token ya tiene un formato válido
                    //comparamos subject de request con username en db
                    var user = userRepository.findByUsername(subject);
                    //forzamos inicio de sesión
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

            filterChain.doFilter(request, response);
        }


}
