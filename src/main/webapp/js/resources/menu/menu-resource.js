/**
 * Created by pceder on 7/14/2014.
 */

(function() {
    var menu = sellInNamespace('sellIn.resources.menu');

    function MenuResource($resource, menuUrl) {
        this.resource = $resource(menuUrl);        
    }

    MenuResource.prototype.get = function(profile) {
        return this.resource.query(profile).$promise;
    };

    menu.MenuResource = MenuResource;
})();