var divRoot = $("#affdex_elements")[0];
var width = 835;
var height = 620;
var faceMode = affdex.FaceDetectorMode.LARGE_FACES;
var reporte = "";
var positivas = 0;
var negativas = 0;
var felicidad = 0;
var tristeza = 0;
var disgusto = 0;
var desprecio = 0;
var enojo = 0;
var miedo = 0;
var sorpresa = 0;
var detector = new affdex.CameraDetector(divRoot, width, height, faceMode);
var emocion = null;

detector.detectAllEmotions();
detector.detectAllExpressions();
detector.detectAllEmojis();
detector.detectAllAppearance();

detector.addEventListener("onInitializeSuccess", function () {
    log('#logs', "Los reportes del detector han comenzado !");
    $("#face_video_canvas").css("display", "block");
    $("#face_video").css("display", "none");
});

document.addEventListener('DOMContentLoaded', function () {
    if (!Notification) {
        alert('Desktop notifications not available in your browser. Try Chromium.');
        return;
    }

    if (Notification.permission !== "granted")
        Notification.requestPermission();
});

function notifyMe(titulo, cuerpo) {
    if (Notification.permission !== "granted")
        Notification.requestPermission();
    else {
        var notification = new Notification(titulo, {
            icon: 'img/bacano.png',
            body: cuerpo,
        });

        notification.onclick = function () {
            descargarReporte(reporte);
        };

    }
}

function log(node_name, msg) {
    $(node_name).append("<span>" + msg + "</span><br />")
}

function onStart() {
    if (detector && !detector.isRunning) {
        $("#logs").html("");
        detector.start();
    }
    log('#logs', "Boton de inicio clickeado !");
}

function onStop() {
    log('#logs', "Boton de detener clickeado !");
    if (detector && detector.isRunning) {
        detector.removeEventListener();
        detector.stop();
    }
};

detector.addEventListener("onWebcamConnectSuccess", function () {
    log('#logs', "Acceso a la camara permitido !");
});

detector.addEventListener("onWebcamConnectFailure", function () {
    log('#logs', "webcam denied");
    console.log("Acceso a la camara denegado !");
});

detector.addEventListener("onStopSuccess", function () {
    log('#logs', "Los reportes del detector se han parado !");
    $("#results").html("");


    var ruta = $("#form-loguear-affectiva").attr("action");
    var usuario =  $("#usuario-affectiva").val();

    $.ajax({
        url: ruta,
        type: "post",
        data: {
            usuario: usuario,
            emocion: emocion
        },
        success: function(datos) {
            notifyMe("ACCESO AUTORIZADO", "Te has logueado con exito !");
            var rutaRedirect = $("#form-autentificar").attr("action");
            window.location.href = rutaRedirect;
        }
    });
});

detector.addEventListener("onImageResultsSuccess", function (faces, image, timestamp) {
    var porcentajeAMostrar = 25;
    $('#results').html("");
    if (faces.length > 0) {
        log("#results", "<h5><strong>Emocion(es) detectada(s): </strong></h5> ");

        if (faces[0].emotions.joy > porcentajeAMostrar) {
            log('#results', "<strong class='alert-link'>Felicidad: </strong> " + faces[0].emotions.joy, function (key, val) {
                return val.toFixed ? Number(val.toFixed(2)) : val;
            });

            positivas += 1;
            felicidad += 1;

            if ((timestamp.toFixed(2) % 5) <= 1) {

                reporte += "Felicidad encontrada con un porcentaje de: " + faces[0].emotions.joy + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.sadness > porcentajeAMostrar) {
            log('#results', "<strong class='text-danger'>Tristeza: </strong> " + faces[0].emotions.sadness, function (key, val) {
                return val.toFixed ? Number(val.toFixed(2)) : val;
            });

            negativas += 1;
            tristeza += 1;

            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Tristeza encontrada con un porcentaje de: " + faces[0].emotions.sadness + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.disgust > porcentajeAMostrar) {
            log('#results', "<strong class='text-danger'>Disgusto: </strong> " + faces[0].emotions.disgust, function (key, val) {
                return val.toFixed ? Number(val.toFixed(0)) : val;
            });

            negativas += 1;
            disgusto += 1;


            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Disgusto encontrado con un porcentaje de: " + faces[0].emotions.disgust + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.contempt > porcentajeAMostrar) {
            log('#results', "<strong class='text-danger'>Desprecio: </strong> " + faces[0].emotions.contempt, function (key, val) {
                return val.toFixed ? Number(val.toFixed(0)) : val;
            });

            negativas += 1;
            desprecio += 1;


            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Desprecio encontrado con un porcentaje de: " + faces[0].emotions.contemp + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.anger > porcentajeAMostrar) {
            log('#results', "<strong class='text-danger'>Enojo: </strong> " + faces[0].emotions.anger, function (key, val) {
                return val.toFixed ? Number(val.toFixed(0)) : val;
            });

            negativas += 1;
            enojo += 1;

            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Enojo encontrado con un porcentaje de: " + faces[0].emotions.anger + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.fear > porcentajeAMostrar) {
            log('#results', "<strong class='text-danger'>Miedo: </strong> " + faces[0].emotions.fear, function (key, val) {
                return val.toFixed ? Number(val.toFixed(0)) : val;
            });

            negativas += 1;
            miedo += 1;

            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Miedo encontrado con un porcentaje de: " + faces[0].emotions.fear + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        if (faces[0].emotions.surprise > porcentajeAMostrar) {
            log('#results', "<strong class='alert-link'>Sorpresa: </strong> " + faces[0].emotions.surprise, function (key, val) {
                return val.toFixed ? Number(val.toFixed(0)) : val;
            });

            positivas += 1;
            sorpresa += 1;

            if ((timestamp.toFixed(2) % 5) <= 1) {
                reporte += "Sorpresa encontrado con un porcentaje de: " + faces[0].emotions.surprise + " en el minuto: " + ((timestamp / 60).toFixed(2)) + ". \n";
            }
        }

        emocion = faces[0].emojis.dominantEmoji;
        log('#results', "<h1 class='mb-0'>" + faces[0].emojis.dominantEmoji + "</h1>");
        drawFeaturePoints(image, faces[0].featurePoints);
    }


});


function drawFeaturePoints(img, featurePoints) {
    var contxt = $('#face_video_canvas')[0].getContext('2d');

    var hRatio = contxt.canvas.width / img.width;
    var vRatio = contxt.canvas.height / img.height;
    var ratio = Math.min(hRatio, vRatio);

    contxt.strokeStyle = "#add8e6";
    for (var id in featurePoints) {
        contxt.beginPath();
        contxt.arc(featurePoints[id].x,
            featurePoints[id].y, 2, 0, 2 * Math.PI);
        contxt.stroke();

    }
}

// $(document).ready(function(){
//     $("#stop").unbind().click(function() {
//     });
// });
