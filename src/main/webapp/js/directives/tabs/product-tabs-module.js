(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    angular.module('sellIn.directives.producttabs', [])
        .controller('productTabsController', productTabs.ProductTabsController)
        .constant('productTabs', [{name: 'ATV', url: 'profiles', isActive: 'active'},
                                  {name: 'Ranger', url: 'profiles', isActive: 'active'},
                                  {name: 'RZR', url: 'profiles', isActive: 'active'},
                                  {name: 'Victory', url: 'profiles', isActive: 'active'},
                                  {name: 'Indian', url: 'profiles', isActive: 'active'}]);
})();