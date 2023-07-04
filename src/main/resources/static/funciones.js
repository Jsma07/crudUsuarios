function eliminar(id){
	swal({
  title: "¿Desea eliminar el cliente?",
  text: "Una vez eliminado no podrá recuperar los datos del cliente",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
  if (OK) {
	  $.ajax({
		  url:"/eliminar/"+id,
		  success: function(res){
			  Console.log(res);
		  }
	  })
    swal("!Los datos de tu cliente han sido eliminados correctamente!", {
      icon: "success",
    }).then((ok)=>{
		if(ok){
			location.href="/clientes";
		}
	})
  } else {
    swal("Los datos de tu cliente no han sido eliminados");
  }
});
	
}