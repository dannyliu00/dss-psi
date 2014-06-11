(function() {
    var reasonModal = sellInNamespace('sellIn.directives.reasonmodal');

    angular.module('sellIn.directives.reasonmodal', ['ngRoute', 'ui.bootstrap'])
        .controller('reasonModalController', reasonModal.ReasonModalController)
    ;
})();