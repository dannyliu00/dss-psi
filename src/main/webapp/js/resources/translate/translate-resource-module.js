/**
 * Created by pceder on 8/8/2014.
 */
(function() {
    var translate = sellInNamespace('sellIn.resources.translate');

    angular.module('sellIn.resources.translate', ['ngResource'])
        .service('translationResource', translate.TranslationResource)
        .constant('translateGetStringsUrl', '/dss-psi/webapi/translation/getResourceStrings')
        .constant('translateAddStringUrl', '/dss-psi/webapi/translation/addResourceString');
})();