(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsController($scope) {
        $scope.productTabs = [{name: 'ATV', content: '2', isActive: 'true'},
                             {name: 'Ranger', content: '6', isActive: 'false'},
                             {name: 'RZR', content: 'Z', isActive: 'false'},
                             {name: 'Victory', content: '5', isActive: 'false'},
                             {name: 'Indian', content: 'F', isActive: 'false'}];
    	}
    productTabs.ProductTabsController = ProductTabsController;
    })();