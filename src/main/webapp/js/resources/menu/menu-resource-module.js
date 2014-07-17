/**
 * Created by pceder on 7/14/2014.
 */
(function() {
    var menu = sellInNamespace('sellIn.resources.menu');

    angular.module('sellIn.resources.menu', ['ngResource'])
        .service('menuResource', menu.MenuResource)
        .constant('menuUrl', '/dss-psi/webapi/menu/menulinks');
})();