/* 
 * Filtrar empleados con ajax
 */

function filtrar() {
    filtro = document.getElementById("filtro").value;
    $.ajax({
        url: "ControladorFiltrarExperiencia",
        method: 'POST',
        data: {filtro : filtro}        
    }).done(function(datos) {
        $("#listado").html(datos);
    });
}
