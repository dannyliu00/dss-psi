(function() {
    var spinnerInput = sellInNamespace('sellIn.directives.spinnerinput');

    function SpinnerInputDirective() {
    	
 	   return {
		   restrict: 'A',
		   require: 'ngModel',
		   link: function(scope, element, attrs, modelCtrl) {
			   $(element).spinner({
				   stop: function (event, ui) {
					   scope.os.actual=parseInt($(this).val());
					   angular.element(this).change();
				   }
			   });
		   }
	   };
    }

    spinnerInput.SpinnerInputDirective = SpinnerInputDirective;
})();