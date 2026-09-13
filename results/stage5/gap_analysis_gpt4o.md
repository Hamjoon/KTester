# Stage 5 gap analysis: gpt-4o, FIX_TRIES=5

## Correct instruction coverage range position

- FIX_TRIES=3 (gpt-4o-mini): inside 80, below 24, above 7 of 111 tasks.
- FIX_TRIES=5 (gpt-4o-mini): inside 75, below 33, above 3 of 111 tasks.
- gpt-4o, FIX_TRIES=5: inside 68, below 23, above 20 of 111 tasks.

## Project-level gaps and repetition spread

A flag means the absolute gap between ours and the authors' five-repetition mean is larger than the authors' maximum-minus-minimum spread.

| Project | Metric | Ours (gpt-4o, FIX_TRIES=5) | Authors mean | Authors min | Authors max | Gap | Authors spread | Flag |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| batch-processing-gateway | TC compile | 74.63% | 100.00% | 100.00% | 100.00% | -25.37 pp | 0.00 pp | yes |
| batch-processing-gateway | TC execution | 43.28% | 83.44% | 75.76% | 87.50% | -40.16 pp | 11.74 pp | yes |
| batch-processing-gateway | Correct inst | 64.17% | 76.83% | 63.00% | 86.83% | -12.67 pp | 23.83 pp | no |
| batch-processing-gateway | Correct branch | 55.17% | 60.70% | 48.00% | 69.50% | -5.53 pp | 21.50 pp | no |
| commons-cli | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-cli | TC execution | 80.56% | 79.04% | 68.75% | 100.00% | +1.51 pp | 31.25 pp | no |
| commons-cli | Correct inst | 98.50% | 74.80% | 53.50% | 97.50% | +23.70 pp | 44.00 pp | no |
| commons-cli | Correct branch | 90.00% | 66.40% | 50.00% | 86.50% | +23.60 pp | 36.50 pp | no |
| commons-codec | TC compile | 88.85% | 100.00% | 100.00% | 100.00% | -11.15 pp | 0.00 pp | yes |
| commons-codec | TC execution | 65.00% | 77.85% | 65.67% | 100.00% | -12.85 pp | 34.33 pp | no |
| commons-codec | Correct inst | 54.17% | 64.93% | 54.00% | 72.61% | -10.77 pp | 18.61 pp | no |
| commons-codec | Correct branch | 46.50% | 56.50% | 49.17% | 61.56% | -10.00 pp | 12.39 pp | no |
| commons-collections | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-collections | TC execution | 70.62% | 89.26% | 83.06% | 100.00% | -18.63 pp | 16.94 pp | yes |
| commons-collections | Correct inst | 47.43% | 56.71% | 55.14% | 58.79% | -9.29 pp | 3.64 pp | yes |
| commons-collections | Correct branch | 38.79% | 47.63% | 44.64% | 50.86% | -8.84 pp | 6.21 pp | yes |
| commons-csv | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-csv | TC execution | 62.32% | 62.13% | 36.84% | 91.89% | +0.19 pp | 55.05 pp | no |
| commons-csv | Correct inst | 70.00% | 56.47% | 40.00% | 62.33% | +13.53 pp | 22.33 pp | no |
| commons-csv | Correct branch | 65.50% | 48.20% | 32.83% | 53.50% | +17.30 pp | 20.67 pp | no |
| datafaker | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| datafaker | TC execution | 89.53% | 88.06% | 82.56% | 93.94% | +1.48 pp | 11.38 pp | no |
| datafaker | Correct inst | 68.33% | 63.57% | 31.33% | 76.17% | +4.77 pp | 44.83 pp | no |
| datafaker | Correct branch | 65.50% | 57.77% | 25.83% | 71.00% | +7.73 pp | 45.17 pp | no |
| gson | TC compile | 91.59% | 100.00% | 100.00% | 100.00% | -8.41 pp | 0.00 pp | yes |
| gson | TC execution | 66.37% | 80.40% | 61.95% | 98.90% | -14.03 pp | 36.95 pp | no |
| gson | Correct inst | 43.90% | 63.46% | 56.90% | 70.10% | -19.56 pp | 13.20 pp | yes |
| gson | Correct branch | 42.00% | 54.30% | 48.25% | 59.75% | -12.30 pp | 11.50 pp | yes |
| jdom2 | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| jdom2 | TC execution | 59.61% | 73.94% | 58.44% | 93.89% | -14.33 pp | 35.45 pp | no |
| jdom2 | Correct inst | 38.29% | 49.94% | 46.62% | 52.67% | -11.66 pp | 6.05 pp | yes |
| jdom2 | Correct branch | 34.19% | 43.43% | 41.38% | 45.38% | -9.24 pp | 4.00 pp | yes |
| ruler | TC compile | 91.79% | 100.00% | 100.00% | 100.00% | -8.21 pp | 0.00 pp | yes |
| ruler | TC execution | 53.85% | 70.76% | 43.75% | 97.53% | -16.91 pp | 53.78 pp | no |
| ruler | Correct inst | 21.75% | 26.45% | 16.56% | 37.12% | -4.70 pp | 20.56 pp | no |
| ruler | Correct branch | 17.00% | 19.21% | 11.25% | 27.38% | -2.21 pp | 16.12 pp | no |
| windward | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| windward | TC execution | 62.16% | 98.46% | 92.31% | 100.00% | -36.30 pp | 7.69 pp | yes |
| windward | Correct inst | 0.00% | 63.70% | 48.50% | 88.50% | -63.70 pp | 40.00 pp | yes |
| windward | Correct branch | 0.00% | 59.00% | 45.50% | 78.00% | -59.00 pp | 32.50 pp | yes |

### Projects flagged

- batch-processing-gateway: 2 of 4 metrics flagged.
- commons-cli: 0 of 4 metrics flagged.
- commons-codec: 1 of 4 metrics flagged.
- commons-collections: 3 of 4 metrics flagged.
- commons-csv: 0 of 4 metrics flagged.
- datafaker: 0 of 4 metrics flagged.
- gson: 3 of 4 metrics flagged.
- jdom2: 2 of 4 metrics flagged.
- ruler: 1 of 4 metrics flagged.
- windward: 3 of 4 metrics flagged.
