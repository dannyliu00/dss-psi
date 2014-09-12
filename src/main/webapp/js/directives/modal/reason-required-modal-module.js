(function () {
    var reasonRequired = sellInNamespace('sellIn.directives.reasonrequired');

    angular.module('sellIn.directives.reasonrequired', ['ngRoute', 'ui.bootstrap'])
        .controller('reasonRequiredController', reasonRequired.ReasonRequiredController)
    ;
})();