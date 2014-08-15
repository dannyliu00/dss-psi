(function () {
    var rsmButton = sellInNamespace('sellIn.directives.rsmbutton');

    angular.module('sellIn.directives.rsmbutton', ['ngRoute', 'ui.bootstrap', 'sellIn.routing.paths', 'sellIn.services.lasttab', 'sellIn.services.translation'])
        .controller('rsmButtonController', rsmButton.RsmButtonController)
        .directive('rsmButton', rsmButton.RsmButtonDirective);
})();