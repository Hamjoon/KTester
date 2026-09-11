# Stage 5 gap analysis: FIX_TRIES=5

## Correct instruction coverage range position

- Inside the authors' five-repetition range: 75 of 111 tasks.
- Below the authors' range: 33 of 111 tasks.
- Above the authors' range: 3 of 111 tasks.

## Project-level gaps and repetition spread

A flag means the absolute gap between ours and the authors' five-repetition mean is larger than the authors' maximum-minus-minimum spread.

| Project | Metric | Ours (FIX_TRIES=5) | Authors mean | Authors min | Authors max | Gap | Authors spread | Flag |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| batch-processing-gateway | TC compile | 87.23% | 100.00% | 100.00% | 100.00% | -12.77 pp | 0.00 pp | yes |
| batch-processing-gateway | TC execution | 61.70% | 83.44% | 75.76% | 87.50% | -21.74 pp | 11.74 pp | yes |
| batch-processing-gateway | Correct inst | 30.33% | 76.83% | 63.00% | 86.83% | -46.50 pp | 23.83 pp | yes |
| batch-processing-gateway | Correct branch | 24.67% | 60.70% | 48.00% | 69.50% | -36.03 pp | 21.50 pp | yes |
| commons-cli | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-cli | TC execution | 70.00% | 79.04% | 68.75% | 100.00% | -9.04 pp | 31.25 pp | no |
| commons-cli | Correct inst | 60.00% | 74.80% | 53.50% | 97.50% | -14.80 pp | 44.00 pp | no |
| commons-cli | Correct branch | 55.50% | 66.40% | 50.00% | 86.50% | -10.90 pp | 36.50 pp | no |
| commons-codec | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-codec | TC execution | 43.33% | 77.85% | 65.67% | 100.00% | -34.52 pp | 34.33 pp | yes |
| commons-codec | Correct inst | 38.39% | 64.93% | 54.00% | 72.61% | -26.54 pp | 18.61 pp | yes |
| commons-codec | Correct branch | 33.78% | 56.50% | 49.17% | 61.56% | -22.72 pp | 12.39 pp | yes |
| commons-collections | TC compile | 97.14% | 100.00% | 100.00% | 100.00% | -2.86 pp | 0.00 pp | yes |
| commons-collections | TC execution | 90.00% | 89.26% | 83.06% | 100.00% | +0.74 pp | 16.94 pp | no |
| commons-collections | Correct inst | 49.21% | 56.71% | 55.14% | 58.79% | -7.50 pp | 3.64 pp | yes |
| commons-collections | Correct branch | 42.93% | 47.63% | 44.64% | 50.86% | -4.70 pp | 6.21 pp | no |
| commons-csv | TC compile | 78.38% | 100.00% | 100.00% | 100.00% | -21.62 pp | 0.00 pp | yes |
| commons-csv | TC execution | 24.32% | 62.13% | 36.84% | 91.89% | -37.80 pp | 55.05 pp | no |
| commons-csv | Correct inst | 23.83% | 56.47% | 40.00% | 62.33% | -32.63 pp | 22.33 pp | yes |
| commons-csv | Correct branch | 19.50% | 48.20% | 32.83% | 53.50% | -28.70 pp | 20.67 pp | yes |
| datafaker | TC compile | 83.58% | 100.00% | 100.00% | 100.00% | -16.42 pp | 0.00 pp | yes |
| datafaker | TC execution | 37.31% | 88.06% | 82.56% | 93.94% | -50.74 pp | 11.38 pp | yes |
| datafaker | Correct inst | 52.83% | 63.57% | 31.33% | 76.17% | -10.73 pp | 44.83 pp | no |
| datafaker | Correct branch | 45.50% | 57.77% | 25.83% | 71.00% | -12.27 pp | 45.17 pp | no |
| gson | TC compile | 81.45% | 100.00% | 100.00% | 100.00% | -18.55 pp | 0.00 pp | yes |
| gson | TC execution | 49.32% | 80.40% | 61.95% | 98.90% | -31.08 pp | 36.95 pp | no |
| gson | Correct inst | 35.40% | 63.46% | 56.90% | 70.10% | -28.06 pp | 13.20 pp | yes |
| gson | Correct branch | 30.90% | 54.30% | 48.25% | 59.75% | -23.40 pp | 11.50 pp | yes |
| jdom2 | TC compile | 85.39% | 100.00% | 100.00% | 100.00% | -14.61 pp | 0.00 pp | yes |
| jdom2 | TC execution | 29.21% | 73.94% | 58.44% | 93.89% | -44.73 pp | 35.45 pp | yes |
| jdom2 | Correct inst | 23.57% | 49.94% | 46.62% | 52.67% | -26.37 pp | 6.05 pp | yes |
| jdom2 | Correct branch | 21.14% | 43.43% | 41.38% | 45.38% | -22.29 pp | 4.00 pp | yes |
| ruler | TC compile | 71.25% | 100.00% | 100.00% | 100.00% | -28.75 pp | 0.00 pp | yes |
| ruler | TC execution | 35.00% | 70.76% | 43.75% | 97.53% | -35.76 pp | 53.78 pp | no |
| ruler | Correct inst | 9.06% | 26.45% | 16.56% | 37.12% | -17.39 pp | 20.56 pp | no |
| ruler | Correct branch | 5.56% | 19.21% | 11.25% | 27.38% | -13.65 pp | 16.12 pp | no |
| windward | TC compile | 64.44% | 100.00% | 100.00% | 100.00% | -35.56 pp | 0.00 pp | yes |
| windward | TC execution | 64.44% | 98.46% | 92.31% | 100.00% | -34.02 pp | 7.69 pp | yes |
| windward | Correct inst | 45.50% | 63.70% | 48.50% | 88.50% | -18.20 pp | 40.00 pp | no |
| windward | Correct branch | 44.00% | 59.00% | 45.50% | 78.00% | -15.00 pp | 32.50 pp | no |

### Projects flagged

- batch-processing-gateway: 4 of 4 metrics flagged.
- commons-cli: 0 of 4 metrics flagged.
- commons-codec: 3 of 4 metrics flagged.
- commons-collections: 2 of 4 metrics flagged.
- commons-csv: 3 of 4 metrics flagged.
- datafaker: 2 of 4 metrics flagged.
- gson: 3 of 4 metrics flagged.
- jdom2: 4 of 4 metrics flagged.
- ruler: 1 of 4 metrics flagged.
- windward: 2 of 4 metrics flagged.
