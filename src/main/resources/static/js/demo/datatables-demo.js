// Call the dataTables jQuery plugin





$(document).ready(function() {
   cargarUsuarios();
  $('#usuarios').DataTable();
});

function getHeaders(){

    return {
     'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization':localStorage.token
    }
}

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers:
      { 'Accept': 'application/json',
                 'Content-Type': 'application/json',
                 'Authorization':localStorage.token}

  });
 const usuarios = await request.json();

  let listadoHTML = '';
  for (let usuario of usuarios){
  let botonEliminar = " <a href'#' onclick='eliminarUsuario("+usuario.id+")' class='btn btn-danger btn-circle btn-sm'><i class='fas fa-trash'></i></a>";
  let usersHTML = "<tr><td>1234</td><td>"+usuario.nombre+" "+usuario.apellido+"</td><td>"+usuario.email+"</td> <td>"+usuario.telefono+"</td> <td>"+botonEliminar+"</td></tr>";

   listadoHTML += usersHTML;
  }


  console.log(usuarios);
  document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
}



async function eliminarUsuario(id){

if(!confirm("Desea eliminar el usuario")){
   return;
}
  const request = await fetch('api/usuarios/'+ id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json',
               'Content-Type': 'application/json',
               'Authorization':localStorage.token
    }
  });

  location.reload()
}