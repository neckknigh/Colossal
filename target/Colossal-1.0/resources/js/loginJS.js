/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        //PF('dlg').jq.effect("shake", {times:5}, 100);
    } else {
        // PF('dlg').hide();
        $('#loginLink').fadeOut();
    }
}

function menuBar(){
   $(".contenido").toggleClass("abrir");
   if(disparador==1){
       $("#barraLateral").hide(300);
       disparador=0;
   }else{
       $("#barraLateral").show(300);
       disparador=1;
   }
   
}