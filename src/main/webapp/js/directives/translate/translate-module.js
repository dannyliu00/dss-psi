/**
 * 
 */
(function(){
	var translate = sellInNamespace('sellIn.directives.translate');
	
	angular.module('sellIn.directives.translate', ['sellIn.resources.translate'])
		.directive('translate', translate.TranslateDirective);
	
})();