(function () {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');

    angular.module('sellIn.directives.reasonmodal', ['ngRoute', 'ui.bootstrap', 'sellIn.resources.reasoncode', 'sellIn.services.translation'])
        .controller('reasonModalController', reasonModal.ReasonModalController)
    ;
})();