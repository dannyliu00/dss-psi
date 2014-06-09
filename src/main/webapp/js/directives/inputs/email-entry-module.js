/**
 * 
 */
(function(){
	var emailEntry = sellInNamespace('sellIn.directives.emailentry');
	
	angular.module('sellIn.directives.emailentry', [])
		.directive('emailEntry', emailEntry.EmailEntryDirective);
	
})();