/**
 * Created by bericks on 5/19/2014.
 */
(function () {
    var commonHeader = sellInNamespace('polaris.directives.commonHeader');

    angular.module('polaris.directives.commonHeader', [
        'sellIn.resources.menu',
        'sellIn.resources.role',
        'sellIn.resources.attribute'])
        .controller('commonHeaderController', commonHeader.CommonHeaderController)
        .directive('commonHeader', commonHeader.CommonHeaderDirective)
        .constant('appTitle', 'Inventory Profiles');

})();