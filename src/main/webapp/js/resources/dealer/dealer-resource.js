(function() {
    var dealer = sellInNamespace('sellIn.resources.dealer');

    function DealerResource($resource, dealerUrl) {
        this.resource = $resource(dealerUrl);
    }

    DealerResource.prototype.get = function(dealer) {
        return this.resource.get(dealer).$promise;
    };

    dealer.DealerResource = DealerResource;
})();