package com.example.curso.controller;
import java.util.ArrayList;
import java.util.List;


import com.example.curso.dao.UsuarioDao;
import com.example.curso.models.Usuario;
import com.example.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    //los controladores Sirven para manejar las url
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Miguel");
        usuario.setApellido("Orjuela");
        usuario.setEmail("orjuelaparramiguel@gmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List <Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){


        if(!validarToken(token)){
            return  null;
        }
        //String usuarioId = jwtUtil.getKey(token);


         /*if (usuarioId == null){
             return  new ArrayList<>();
         }*/
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String usuarioId =jwtUtil.getKey(token);

        return usuarioId != null;


    }


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void  eliminar(@RequestHeader(value="Authorization") String token,@PathVariable Long id){

        if(!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);

    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1,1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }
   /* @RequestMapping(value = "usuario/{id}")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setApellido("Orjuela");
        usuario.setEmail("orjuelaparramiguel@gmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }

    @RequestMapping(value = "usuario")
    public Usuario eliminar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setApellido("Orjuela");
        usuario.setEmail("orjuelaparramiguel@gmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }

    @RequestMapping(value = "usuario")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setApellido("Orjuela");
        usuario.setEmail("orjuelaparramiguel@gmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }*/
}
