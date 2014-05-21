(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    function DealerProfilesResource($resource, profilesUrl) {
        this.resource = $resource(profilesUrl);
    }

    DealerProfilesResource.prototype.query = function(dealer) {
        return this.resource.query(dealer).$promise;
    };

    DealerProfilesResource.prototype.get = function(profile) {
        return this.resource.get(profile).$promise;
    };

    dealerProfiles.DealerProfilesResource = DealerProfilesResource;
})();
