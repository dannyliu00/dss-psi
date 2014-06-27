(function() {
    var dealerProfileDetails = sellInNamespace('sellIn.resources.dealerProfileDetails');

    function DealerProfileDetailsResource($resource, detailsUrl) {
        this.resource = $resource(detailsUrl, {}, {'save': {method: 'POST', isArray: true}});
    }

    DealerProfileDetailsResource.prototype.save = function(details) {
        return this.resource.save(details).$promise;
    };

    dealerProfileDetails.DealerProfileDetailsResource = DealerProfileDetailsResource;
})();
