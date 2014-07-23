(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope, productTabs, appRoleResource) {
    	
	var currentTabs = [];
	
	appRoleResource.get().then(function(role) {
		for(var i = 0; i < productTabs.length; i++) {
	    		if(productTabs[i].content === '2' && $scope.role.sessionDetail.ATV === 'Y') {
	    			currentTabs.push(productTabs[i]);
				} else if(productTabs[i].content === '5' && $scope.role.sessionDetail.VIC === 'Y') {
					currentTabs.push(productTabs[i]);
				} else if(productTabs[i].content === '6' && $scope.role.sessionDetail.RGR === 'Y') {
					currentTabs.push(productTabs[i]);
				} else if(productTabs[i].content === 'Z' && $scope.role.sessionDetail.RZR === 'Y') {
					currentTabs.push(productTabs[i]);
				} else if(productTabs[i].content === 'F' && $scope.role.sessionDetail.IND === 'Y') {
					currentTabs.push(productTabs[i]);
				}
			}
		});
			
	$scope.productTabs = function() {
		return currentTabs;
	};
    	
    }
    productTabs.ProductTabsController = ProductTabsController;
    })();