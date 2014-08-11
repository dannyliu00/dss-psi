(function () {
    var rsmButton = sellInNamespace('sellIn.directives.rsmbutton');

    angular.module('sellIn.directives.rsmbutton', ['ngRoute', 'ui.bootstrap', 'sellIn.routing.paths', 'sellIn.services.lasttab'])
        .controller('rsmButtonController', rsmButton.RsmButtonController)
        .directive('rsmButton', rsmButton.RsmButtonDirective);
})();