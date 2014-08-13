/**
 * Created by pceder on 8/8/2014.
 */

(function() {
    var translate = sellInNamespace('sellIn.resources.translate');

    function TranslationResource($resource, translateGetStringsUrl,translateAddStringUrl) {

        this.resource = $resource(translateGetStringsUrl, {}, {
            addString: {method: 'POST', url: translateAddStringUrl}
        });
        
    }

    TranslationResource.prototype.get = function (item) {
        return this.resource.get(item).$promise;
    };

    TranslationResource.prototype.addString = function (item) {
        return this.resource.addString(item).$promise;
    };

    translate.TranslationResource = TranslationResource;
})();