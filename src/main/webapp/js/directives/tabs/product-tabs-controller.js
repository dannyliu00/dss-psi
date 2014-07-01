(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope,productTabs) {

    	$scope.productTabs = productTabs;
        
    	}
    productTabs.ProductTabsController = ProductTabsController;
    })();