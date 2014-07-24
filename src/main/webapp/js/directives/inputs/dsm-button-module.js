(function() {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    angular.module('sellIn.directives.dsmbutton', ['ngRoute', 'ui.bootstrap', 'sellIn.routing.paths', 'sellIn.services.lasttab'])
        .controller('dsmButtonController', dsmButton.DsmButtonController)
        .directive('dsmButton', dsmButton.DsmButtonDirective);
})();