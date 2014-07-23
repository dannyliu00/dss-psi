(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    angular.module('sellIn.directives.producttabs', ['sellIn.resources.role'])
    	.directive('productTabs',productTabs.ProductTabsDirective)
        .controller('productTabsController', productTabs.ProductTabsController)
        .constant('productTabs',[{name: 'ATV', content: '2'},
                   {name: 'Ranger', content: '6'},
                   {name: 'RZR', content: 'Z'},
                   {name: 'Victory', content: '5'},
                   {name: 'Indian', content: 'F'}]);
})();