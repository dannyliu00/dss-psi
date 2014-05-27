(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    function DealerProfileResource($resource, profileUrl) {
        this.resource = $resource(profileUrl);
    }

    DealerProfileResource.prototype.get = function(profile) {
        return this.resource.get(profile).$promise;
    };

    dealerProfile.DealerProfileResource = DealerProfileResource;
})();
