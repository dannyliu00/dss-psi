(function() {
    var spinnerInput = sellInNamespace('sellIn.directives.spinnerinput');

    function SpinnerInputDirective() {
    	
 	   return {
		   restrict: 'A',
		   require: 'ngModel',
		   link: function(scope, element, attrs, modelCtrl) {
			   $(element).spinner({
				   spin: function (event, ui) {
					   $(this).change();
				   }
			   });
		   }
	   };
    }

    spinnerInput.SpinnerInputDirective = SpinnerInputDirective;
})();