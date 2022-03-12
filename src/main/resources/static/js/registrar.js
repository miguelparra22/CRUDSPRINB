// Call the dataTables jQuery plugin





$(document).ready(function() {
 //Onready
});



async function registrarUsuarios(){

  let datos ={};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.apellido = document.getElementById("txtApellido").value;
  datos.email = document.getElementById("txtEmail").value;
  datos.password = document.getElementById("txtPassword").value;
  let repetirPassword = document.getElementById("txtRepeatPassword").value

  if(repetirPassword != datos.password){
      alert('La contraseña no es la misma')
      return; //al poner el return sin accion corta la funcion
  }
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });

  alert("Usuario creado correctamente");
  window.location.href = "login.html"
 //const usuarios = await request.json();

}

