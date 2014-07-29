(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    angular.module('sellIn.directives.producttabs', ['sellIn.resources.role'])
    	.directive('productTabs',productTabs.ProductTabsDirective)
        .controller('productTabsController', productTabs.ProductTabsController)
        .constant('productTabs',[
            {name: 'ATV', content: '2', isActive: ''},
            {name: 'Ranger', content: '6', isActive: ''},
            {name: 'RZR', content: 'Z', isActive: ''},
            {name: 'Victory', content: '5', isActive: ''},
            {name: 'Indian', content: 'F', isActive: ''}]);
})();