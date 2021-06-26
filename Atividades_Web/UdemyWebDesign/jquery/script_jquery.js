var counter = 0;
var auxJequiti = true;

$(function() {
    
    $('#btn-esconder').click(function(){
        $('#cabecalho').hide();
    });
    
    $('#btn-exibir').click(function(){
        if(counter==0) {
            $('#cabecalho').css("color","red");
            counter += 1;
        } else {
            $('#cabecalho').css("color","blue");
            counter -= 1;
        }
        $('#cabecalho').show();
        
    });
    
    $('#btn-fade-out').click(function(){
        $('#fade-out').fadeOut();
    });
    
    $('#btn-fade-in').click(function(){
        $('#fade-out').fadeIn();
    });
    
    $('#btn-jequiti').click(function() {
        if(auxJequiti == true)
            $('#jequiti').show();
        else 
            $('#jequiti').hide();
        auxJequiti = !auxJequiti;
    });
    
    $("#l1").click(function() {
        $("#i1").show();
        $("#i2").hide();
        $("#i3").hide();
        $("#i4").hide();
        $("#i5").hide();
    });
    
    $("#l2").click(function() {
        $("#i1").hide();
        $("#i2").show();
        $("#i3").hide();
        $("#i4").hide();
        $("#i5").hide();
    });
    
    $("#l3").click(function() {
        $("#i1").hide();
        $("#i2").hide();
        $("#i3").show();
        $("#i4").hide();
        $("#i5").hide();
    });
    
    $("#l4").click(function() {
        $("#i1").hide();
        $("#i2").hide();
        $("#i3").hide();
        $("#i4").show();
        $("#i5").hide();
    });
    
    $("#l5").click(function() {
        $("#i1").hide();
        $("#i2").hide();
        $("#i3").hide();
        $("#i4").hide();
        $("#i5").show();
    });
});

