(function(){
	var numbersOnly = sellInNamespace('sellIn.directives.numbersonly');
	
	function NumbersOnlyDirective() {
	   return {
		   restrict: 'A',
		   require: 'ngModel',
		   link: function(scope, element, attrs, modelCtrl) {
			   	modelCtrl.$parsers.push(function (inputValue) {
			   		var transformedInput = inputValue.replace(/[^0-9]/g, '');
			   		if(isNaN(transformedInput) || transformedInput.length === 0) {
			   			transformedInput = 0;
			   		};
			   		transformedInput = parseInt(transformedInput);
			   		transformedInput += '';
			   		if (transformedInput!=inputValue) {
			   			modelCtrl.$setViewValue(transformedInput);
			   			modelCtrl.$render();
			   		}         

			   		return transformedInput;         
			   	});
		   }
	   };
	}
	numbersOnly.NumbersOnlyDirective = NumbersOnlyDirective;
})();