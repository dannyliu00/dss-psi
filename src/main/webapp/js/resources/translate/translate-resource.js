/**
 * Created by pceder on 8/8/2014.
 */

(function() {
    var translate = sellInNamespace('sellIn.resources.translate');

    function TranslationResource($resource, translateUrl) {
        this.resource = $resource(translateUrl);        
    }

    TranslationResource.prototype.get = function() {
        return this.resource.get().$promise;
    };

    translate.TranslationResource = TranslationResource;
})();