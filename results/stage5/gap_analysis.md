# Stage 5 gap analysis

## Correct instruction coverage range position

- Inside the authors' five-repetition range: 80 of 111 tasks.
- Below the authors' range: 24 of 111 tasks.
- Above the authors' range: 7 of 111 tasks.

### Ten worst gaps

| Rank | Project | Task | Ours | Authors mean | Authors min | Authors max | Gap | In range? |
| ---: | --- | --- | ---: | ---: | ---: | ---: | ---: | --- |
| 1 | datafaker | FakeValues_toJavaNames | 0.00 | 0.99 | 0.96 | 1.00 | -0.99 | no |
| 2 | commons-collections | SequencesComparator_getMiddleSnake | 0.00 | 0.89 | 0.69 | 0.98 | -0.89 | no |
| 3 | gson | LinkedTreeMap_find | 0.00 | 0.83 | 0.64 | 0.91 | -0.83 | no |
| 4 | batch-processing-gateway | ApplicationSubmissionHelper_populateEnv | 0.00 | 0.76 | 0.22 | 1.00 | -0.76 | no |
| 5 | commons-codec | DaitchMokotoffSoundex_soundex | 0.00 | 0.75 | 0.00 | 0.95 | -0.75 | yes |
| 6 | gson | LinkedTreeMap_rebalance | 0.00 | 0.73 | 0.58 | 0.83 | -0.73 | no |
| 7 | batch-processing-gateway | SubmissionSummary_copyFrom | 0.00 | 0.71 | 0.00 | 1.00 | -0.71 | yes |
| 8 | gson | ObjectTypeAdapter_read | 0.00 | 0.70 | 0.32 | 1.00 | -0.70 | no |
| 9 | commons-csv | CSVParser_createHeaders | 0.00 | 0.68 | 0.59 | 0.79 | -0.68 | no |
| 10 | jdom2 | AbstractStAXStreamProcessor_printContent | 0.00 | 0.65 | 0.34 | 0.91 | -0.65 | no |

### Ten best gaps

| Rank | Project | Task | Ours | Authors mean | Authors min | Authors max | Gap | In range? |
| ---: | --- | --- | ---: | ---: | ---: | ---: | ---: | --- |
| 1 | gson | JsonReader_peek | 0.88 | 0.47 | 0.14 | 0.94 | +0.41 | yes |
| 2 | gson | $Gson$Types_getGenericSupertype | 0.87 | 0.55 | 0.31 | 0.71 | +0.32 | no |
| 3 | jdom2 | DTDParser_formatInternal | 0.85 | 0.56 | 0.00 | 0.96 | +0.29 | yes |
| 4 | gson | $Gson$Types_resolve | 0.68 | 0.42 | 0.10 | 0.78 | +0.26 | yes |
| 5 | datafaker | FakeValuesService_resolveExpression_2 | 0.79 | 0.54 | 0.15 | 0.80 | +0.25 | yes |
| 6 | ruler | MachineComplexityEvaluator_evaluate | 0.53 | 0.33 | 0.32 | 0.34 | +0.20 | no |
| 7 | datafaker | FakeValuesService_javaNameToYamlName | 0.98 | 0.78 | 0.00 | 0.98 | +0.20 | yes |
| 8 | commons-codec | DoubleMetaphone_handleG | 0.48 | 0.28 | 0.12 | 0.37 | +0.20 | no |
| 9 | ruler | ByteMachine_updateMatchReferences | 0.25 | 0.08 | 0.00 | 0.39 | +0.17 | yes |
| 10 | gson | JsonReader_nextNonWhitespace | 0.71 | 0.54 | 0.35 | 0.83 | +0.17 | yes |

## Project-level gaps and repetition spread

A flag means the absolute gap between ours and the authors' five-repetition mean is larger than the authors' maximum-minus-minimum spread.

| Project | Metric | Ours | Authors mean | Authors min | Authors max | Gap | Authors spread | Flag |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |
| batch-processing-gateway | TC compile | 66.67% | 100.00% | 100.00% | 100.00% | -33.33 pp | 0.00 pp | yes |
| batch-processing-gateway | TC execution | 41.67% | 83.44% | 75.76% | 87.50% | -41.77 pp | 11.74 pp | yes |
| batch-processing-gateway | Correct inst | 31.33% | 76.83% | 63.00% | 86.83% | -45.50 pp | 23.83 pp | yes |
| batch-processing-gateway | Correct branch | 28.83% | 60.70% | 48.00% | 69.50% | -31.87 pp | 21.50 pp | yes |
| commons-cli | TC compile | 100.00% | 100.00% | 100.00% | 100.00% | +0.00 pp | 0.00 pp | no |
| commons-cli | TC execution | 63.41% | 79.04% | 68.75% | 100.00% | -15.63 pp | 31.25 pp | no |
| commons-cli | Correct inst | 82.00% | 74.80% | 53.50% | 97.50% | +7.20 pp | 44.00 pp | no |
| commons-cli | Correct branch | 69.00% | 66.40% | 50.00% | 86.50% | +2.60 pp | 36.50 pp | no |
| commons-codec | TC compile | 96.79% | 100.00% | 100.00% | 100.00% | -3.21 pp | 0.00 pp | yes |
| commons-codec | TC execution | 48.17% | 77.85% | 65.67% | 100.00% | -29.69 pp | 34.33 pp | no |
| commons-codec | Correct inst | 47.72% | 64.93% | 54.00% | 72.61% | -17.21 pp | 18.61 pp | no |
| commons-codec | Correct branch | 45.33% | 56.50% | 49.17% | 61.56% | -11.17 pp | 12.39 pp | no |
| commons-collections | TC compile | 82.35% | 100.00% | 100.00% | 100.00% | -17.65 pp | 0.00 pp | yes |
| commons-collections | TC execution | 71.90% | 89.26% | 83.06% | 100.00% | -17.36 pp | 16.94 pp | yes |
| commons-collections | Correct inst | 41.93% | 56.71% | 55.14% | 58.79% | -14.79 pp | 3.64 pp | yes |
| commons-collections | Correct branch | 36.29% | 47.63% | 44.64% | 50.86% | -11.34 pp | 6.21 pp | yes |
| commons-csv | TC compile | 69.81% | 100.00% | 100.00% | 100.00% | -30.19 pp | 0.00 pp | yes |
| commons-csv | TC execution | 32.08% | 62.13% | 36.84% | 91.89% | -30.05 pp | 55.05 pp | no |
| commons-csv | Correct inst | 40.50% | 56.47% | 40.00% | 62.33% | -15.97 pp | 22.33 pp | no |
| commons-csv | Correct branch | 36.67% | 48.20% | 32.83% | 53.50% | -11.53 pp | 20.67 pp | no |
| datafaker | TC compile | 73.26% | 100.00% | 100.00% | 100.00% | -26.74 pp | 0.00 pp | yes |
| datafaker | TC execution | 67.44% | 88.06% | 82.56% | 93.94% | -20.61 pp | 11.38 pp | yes |
| datafaker | Correct inst | 42.67% | 63.57% | 31.33% | 76.17% | -20.90 pp | 44.83 pp | no |
| datafaker | Correct branch | 40.17% | 57.77% | 25.83% | 71.00% | -17.60 pp | 45.17 pp | no |
| gson | TC compile | 92.67% | 100.00% | 100.00% | 100.00% | -7.33 pp | 0.00 pp | yes |
| gson | TC execution | 65.52% | 80.40% | 61.95% | 98.90% | -14.88 pp | 36.95 pp | no |
| gson | Correct inst | 55.55% | 63.46% | 56.90% | 70.10% | -7.91 pp | 13.20 pp | no |
| gson | Correct branch | 50.40% | 54.30% | 48.25% | 59.75% | -3.90 pp | 11.50 pp | no |
| jdom2 | TC compile | 75.77% | 100.00% | 100.00% | 100.00% | -24.23 pp | 0.00 pp | yes |
| jdom2 | TC execution | 40.72% | 73.94% | 58.44% | 93.89% | -33.22 pp | 35.45 pp | no |
| jdom2 | Correct inst | 26.57% | 49.94% | 46.62% | 52.67% | -23.37 pp | 6.05 pp | yes |
| jdom2 | Correct branch | 23.67% | 43.43% | 41.38% | 45.38% | -19.76 pp | 4.00 pp | yes |
| ruler | TC compile | 67.92% | 100.00% | 100.00% | 100.00% | -32.08 pp | 0.00 pp | yes |
| ruler | TC execution | 25.47% | 70.76% | 43.75% | 97.53% | -45.29 pp | 53.78 pp | no |
| ruler | Correct inst | 6.81% | 26.45% | 16.56% | 37.12% | -19.64 pp | 20.56 pp | no |
| ruler | Correct branch | 4.81% | 19.21% | 11.25% | 27.38% | -14.40 pp | 16.12 pp | no |
| windward | TC compile | 32.00% | 100.00% | 100.00% | 100.00% | -68.00 pp | 0.00 pp | yes |
| windward | TC execution | 24.00% | 98.46% | 92.31% | 100.00% | -74.46 pp | 7.69 pp | yes |
| windward | Correct inst | 49.00% | 63.70% | 48.50% | 88.50% | -14.70 pp | 40.00 pp | no |
| windward | Correct branch | 45.50% | 59.00% | 45.50% | 78.00% | -13.50 pp | 32.50 pp | no |

### Projects flagged

- batch-processing-gateway: 4 of 4 metrics flagged.
- commons-cli: 0 of 4 metrics flagged.
- commons-codec: 1 of 4 metrics flagged.
- commons-collections: 4 of 4 metrics flagged.
- commons-csv: 1 of 4 metrics flagged.
- datafaker: 2 of 4 metrics flagged.
- gson: 1 of 4 metrics flagged.
- jdom2: 3 of 4 metrics flagged.
- ruler: 1 of 4 metrics flagged.
- windward: 2 of 4 metrics flagged.
