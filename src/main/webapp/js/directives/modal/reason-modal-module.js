(function () {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');

    angular.module('sellIn.directives.reasonmodal', ['ngRoute', 'ui.bootstrap', 'sellIn.resources.reasoncode'])
        .controller('reasonModalController', reasonModal.ReasonModalController)
    ;
})();