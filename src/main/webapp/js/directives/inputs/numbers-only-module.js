/**
 *
 */
(function () {
    var numbersOnly = sellInNamespace('sellIn.directives.numbersonly');

    angular.module('sellIn.directives.numbersonly', [])
        .directive('numbersOnly', numbersOnly.NumbersOnlyDirective);

})();