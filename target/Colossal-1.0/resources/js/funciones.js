/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function toggle() {
    jQuery("#menu-toggle").click(function (e) {
        e.preventDefault();
        jQuery("#wrapper").toggleClass("toggled");
    });
}

function tooltip() {
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
}

var sw = false;
function controlExampleDetails(exampleID) {
    $(".exampleDiv" + exampleID).show();
}

var sw_collapse1 = true;
var sw_collapse2 = true;
var sw_collapse3 = false;
var sw_collapse4 = true;
function setCollapse(i) {
    var sw_collapse;
    $('#icon_menu' + i).toggleClass("glyphicon-menu-up glyphicon-menu-down");
    if (i == 1) {
        sw_collapse = sw_collapse1;
    } else if (i == 2) {
        sw_collapse = sw_collapse2;
    } else if (i == 3) {
        sw_collapse = sw_collapse3;
    } else {
        sw_collapse = sw_collapse4;
    }
    if (sw_collapse) {
        $("#collider" + i).collapse("hide");
        if (i == 1) {
            sw_collapse1 = false;
        } else if (i == 2) {
            sw_collapse2 = false;
        } else if (i == 3) {
            sw_collapse3 = false;
        } else {
            sw_collapse4 = false;
        }
    } else {
        $("#collider" + i).collapse("show");
        if (i == 1) {
            sw_collapse1 = true;
        } else if (i == 2) {
            sw_collapse2 = true;
        } else if (i == 3) {
            sw_collapse3 = true;
        } else {
            sw_collapse4 = true;
        }
    }
}

var panel_collapse1 = false;
var panel_collapse2 = false;


function editarParametro(i) {
    var panel_collapse;
    if (i == 1) {
        panel_collapse = panel_collapse1;
    } else {
        panel_collapse = panel_collapse2;
    }
    if (!panel_collapse) {
        $("#panelCollapse" + i).collapse("show");
    }
}
function panelCollapse(i) {
    var panel_collapse;
    if (i == 1) {
        panel_collapse = panel_collapse1;
    } else {
        panel_collapse = panel_collapse2;
    }

    if (panel_collapse) {
        $("#panelCollapse" + i).collapse("hide");
        if (i == 1) {
            panel_collapse1 = false;
        } else {
            panel_collapse2 = false;
        }
    } else {
        $("#panelCollapse" + i).collapse("show");
        if (i == 1) {
            panel_collapse1 = true;
        } else {
            panel_collapse2 = true;
        }
    }
}



jQuery.fn.prettify = function () {
    this.html(prettyPrintOne(this.html()));
};

function correctorClick(i) {
    var updateButton = $("boton" + i);
    updateButton.jq.click();
}

function desbloquearOverlayPanel() {

//    PF('overlayPanelList').loadContents();
    console.log("entro en el descloqueo");
    PF('bui').hide();
    PF('overlayPanelList').hide();
}

function hideTooltip() {
    $("toolTipFade").hide();
}

function eliminarLogo() {
    files = null;
}

var images = [];
var m = 0;
function saveImages(input) {

    if (input.files) {
        if (input.files[0].type === 'image/jpeg' || input.files[0].type === 'image/png') {
            images[m] = input.files[0];
            m++;
        }

    }
//    console.log(images);
}

function loadImage(idImagen, index) {
    var reader = new FileReader();

    reader.readAsDataURL(images[index]);
    reader.onload = function () {
        var output = document.getElementById(idImagen);
        output.src = reader.result;
    };
}

function removeImage(index) {
    images.splice(index, 1);
    m--;
//    console.log(images);
}



var files;
var loadFile = function (event, flag) {
    var reader = new FileReader();
    var output = document.getElementById('output');
    reader.onload = function () {
        output = document.getElementById('output');
        output.src = reader.result;
    };


    if (flag) {

        if (event.target.files[0].type === 'image/jpeg' || event.target.files[0].type === 'image/png') {
            files = event.target.files[0];
        }


    }

    if (files) {

        if (files.type === 'image/jpeg' || files.type === 'image/png') {

            reader.readAsDataURL(files);
//            i=0;
        } else {
            if (output) {
                output.src = "";
            }

        }
    }




};


function guardarLogo(input) {
    if (input.files) {

        if (input.files[0].type === 'image/jpeg' || input.files[0].type === 'image/png') {
            files = input.files[0];
        }

    }
}

var cargarLogo = function () {
    var reader = new FileReader();

    reader.onload = function () {
        output = document.getElementById('output');
        output.src = reader.result;
    };
    try {
        reader.readAsDataURL(files);
    } catch (err) {

    }

};

var checkCategories = function (checkboxes) {
//    alert(checkboxes);

    var checkboxArray = checkboxes.split(',');
//    console.log(checkboxArray)
    checkboxArray.pop();
    checkboxArray.forEach(
            function (item) {

                PF(item).check();


            });

};


function changeSrcImage(img) {
    $("#imagen").fadeOut(300, function () {
        $("#imagen").attr('src', img);
    })
            .fadeIn(400);
}

function toogleClassSpan(ident) {
    if (ident == 1) {
        $("#span-historial").toggleClass("glyphicon-chevron-down  glyphicon-chevron-up");
    } else if (ident == 2) {
        $("#span-incidencia").toggleClass("glyphicon-chevron-down  glyphicon-chevron-up");
    }
}


function checkDescripcion(textarea) {
    alert(textarea.value);

    console.log(document.getElementById('form:linkUpdate').disabled);
}


function enviarAlerta(msj) {
    $('#aviso').fadeIn(200, function () {
        $(this).html(" <span class='glyphicon glyphicon-warning-sign' style='color: red'/> " + msj + "<br/><br/>").fadeOut(8000);
        isShowAlert = false;
    });
}

function desactivarEnter() {
  
    if (event.keyCode === 13) {
        event.preventDefault();
    }
}

function checkearBox(box) {
  
    document.getElementById(box).checked=true;
    document.getElementById(box).blur();
}

function inicializarDatepickers() {
    console.log("Date pickers iniciados");
        $(".datepicker1").datepicker({ dateFormat: 'dd/mm/yy' });
        $(".datepicker2").datepicker({ dateFormat: 'dd/mm/yy' });
}

function triggerBtnToken() {
    $('#formToken\\:btnToken').click();
//     PF('btnToken').click();
}








