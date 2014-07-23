/**
 * 
 */
(function(){
	var spinnerInput = sellInNamespace('sellIn.directives.spinnerinput');
	
	angular.module('sellIn.directives.spinnerinput', [])
		.directive('spinnerInput', spinnerInput.SpinnerInputDirective);
	
})();