(function () {
    var dealerProfileDetails = sellInNamespace('sellIn.resources.dealerProfileDetails');

    function DealerProfileDetailsResource($resource, detailsSaveUrl, detailsSubmitUrl) {
        this.resource = $resource(detailsSubmitUrl, {}, {
            save: {method: 'POST', url: detailsSaveUrl},
            submit: {method: 'POST', url: detailsSubmitUrl}
        });
    }

    DealerProfileDetailsResource.prototype.save = function (details) {
        return this.resource.save(details).$promise;
    };

    DealerProfileDetailsResource.prototype.submit = function (details) {
        return this.resource.submit(details).$promise;
    };

    dealerProfileDetails.DealerProfileDetailsResource = DealerProfileDetailsResource;
})();
