(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    function DealerProfilesResource($resource, profilesUrl) {
        this.resource = $resource(profilesUrl, {
            ran: function() {
                // parameter added to URL to work around IE caching mechanism
                return new Date().getTime();
            }
        });
    }

    DealerProfilesResource.prototype.query = function(profile) {
        return this.resource.query(profile).$promise;
    };

    dealerProfiles.DealerProfilesResource = DealerProfilesResource;
})();
