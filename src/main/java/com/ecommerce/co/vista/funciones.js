function consultar() {
    $.ajax (
                {
                    //url         :   'https://gc28f183a191bc4-paises.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/planeta/planeta',// 'poner el manejador de consulta GET',
                    // url         : 'http://ip maquina virtual:8080/api/planetas',
                    url         : 'http://localhost:5000/productos',  //direccion maquina local
                    type        :   'GET',
                    dataType    :   'json',// tipo de formato que devuelve el servicio web,



                    success     :   function(json){

                                        $("#idDivConsulta").empty();

                                        $("#idDivConsulta").append("<table>");
                                        $("#idDivConsulta").append("<caption>Tabla de planetas</caption>");
                                        $("#idDivConsulta").append("<tr><th>Elemento</th><th>Description</th></tr>");
                                        for(i=0; i< json.length; i++){
                                            $("#idDivConsulta").append("<tr>");
                                            $("#idDivConsulta").append("<td>"+json[i].codigoP +"</td>");
                                            $("#idDivConsulta").append("<td>"+json[i].nombreP +"</td>");
                                            $("#idDivConsulta").append("</tr>");
                                        }
                                        $("#idDivConsulta").append("</table>");
                                        console.log(json)
                                    },
                    error       :   function(xhr,status){
                                        console.log(xhr)
                                    }
                }

           );

}

function insertar(){
    var planeta;
    planeta = {codigo:2, nombre:'VENUS'};

    $.ajax (
        {
            url         :   'https://gc28f183a191bc4-paises.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/planeta/planeta',// 'poner el manejador de consulta GET',
            type        :   'POST',
            data        :   planeta,// tipo de formato que devuelve el servicio web,
            success     :   function(response){
                                console.log(response);
                            },
            error       :   function(xhr,status){
                                console.log(xhr);
                            }
        }

   );

}

function borrar(){
    var planeta,datosEnvio;
    planeta     = {codigo : 3};
    datosEnvio  = JSON.stringify(planeta);

    $.ajax (
        {
            url         :   'https://gc28f183a191bc4-paises.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/planeta/planeta',// 'poner el manejador de consulta GET',
            type        :   'DELETE',
            data        :   datosEnvio,// tipo de formato que devuelve el servicio web,
            contentType :   'application/json',
            success     :   function(response){
                                console.log(response);
                            },
            error       :   function(xhr,status){
                                console.log(xhr);
                            }
        }

   );
}

function actualizar() {
    planeta     = {codigo:2, nombre:'VENUS'};
    datosEnvio  = JSON.stringify(planeta);

    $.ajax (
        {
            url         :   'https://gc28f183a191bc4-paises.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/planeta/planeta',// 'poner el manejador de consulta GET',
            type        :   'PUT',
            data        :   datosEnvio,// tipo de formato que devuelve el servicio web,
            contentType :   'application/json',
            success     :   function(response){
                                console.log(response);
                            },
            error       :   function(xhr,status){
                                console.log(xhr);
                            }
        }

   );
}

function consultarId() {

    var codigo =$("#idCodigo").val();

    $.ajax (
        {
            url         :   'https://gc28f183a191bc4-paises.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/planeta/planeta/:id' + codigo,// 'poner el manejador de consulta GET',
            type        :   'GET',
            dataType    :   'json',// tipo de formato que devuelve el servicio web,
            success     :   function(json){
                                $("#idDivConsulta").empty();
                                for(i=0; i< json.items.length; i++){
                                $("#idDivConsulta").append(json.items[i].codigo + json.items[i].nombre + " ");
                                }
                                console.log(json)
                            },
            error       :   function(xhr,status){
                                console.log(json)
                            }
        }

   );


}