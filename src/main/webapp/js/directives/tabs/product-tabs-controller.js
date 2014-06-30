(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope,productTabs) {

    	$scope.productTabs = productTabs;
        
        $scope.tabContent = function(activeContent) {
        	if(activeContent != null){
        		$scope.activeTabFilter = activeContent;
        	} else {
        		$scope.activeTabFilter = "2";
        		}
        	};
    	}
    productTabs.ProductTabsController = ProductTabsController;
    })();