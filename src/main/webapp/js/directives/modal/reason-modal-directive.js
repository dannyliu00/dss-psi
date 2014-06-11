(function(){
	var reasonCode = sellInNamespace('sellIn.directives.reasoncode');
	
	function ReasonCodeDirective() {
	   return {
		   restrict: 'E',
		   controller: 'reason-modal-controller',
		   link: function(scope, element, attrs) {
			   
			   scope.$watch('submitRequests', function() {
				   if(angular.element("noncompliant") === "true"){
					   $scope.reasonModalInstance();    
                   }
               });
			   	}
		   };
	   }
	reasonCode.ReasonCodeDirective = ReasonCodeDirective;
})();