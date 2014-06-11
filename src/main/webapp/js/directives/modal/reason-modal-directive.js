(function(){
	var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');
	
	function ReasonModalDirective() {
	   return {
		   restrict: 'E',
		   controller: 'reason-modal-controller'
		   };
	   }
	reasonModal.ReasonModalDirective = ReasonModalDirective;
})();