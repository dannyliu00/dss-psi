(function () {
	angular.module('sellIn.services.lasttab',[])
		.factory('lastTab', function lastTabFactory() {
		
		var lastTab = {};
		
		lastTab.tab = '';
		
		lastTab.changeType = function(type) {
			lastTab.tab = type;
		};
	
		return lastTab;
	});
})();