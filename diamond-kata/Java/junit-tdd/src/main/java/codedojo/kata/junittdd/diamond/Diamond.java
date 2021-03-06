/*
 * This file is part of the DiamondKata application.
 *
 * © Stefan Petcu <hello@stefanpetcu.com>
 *
 * For the full copyright and license information, please view
 * the LICENSE file that was distributed with this source code.
 */
package codedojo.kata.junittdd.diamond;

class Diamond {
    private final Alphabet alphabet;
    private final Character character;

    Diamond(Alphabet alphabet) {
        this(alphabet, null);
    }

    Diamond(Alphabet alphabet, Character character) {
        this.alphabet = alphabet;
        this.character = null == character ? alphabet.lastCharacter() : character;
    }

    @Override
    public String toString() {
        StringBuilder rendering = new StringBuilder();

        int characterIndex, firstCharacterPosition, lastCharacterPosition, verticalPosition;

        characterIndex = firstCharacterPosition = lastCharacterPosition = alphabet.indexOf(character);
        verticalPosition = 0;

        while (verticalPosition <= characterIndex) {
            renderRow(rendering, verticalPosition, firstCharacterPosition, lastCharacterPosition);

            if (verticalPosition == characterIndex) {
                break;
            }

            --firstCharacterPosition;
            ++lastCharacterPosition;
            ++verticalPosition;

            rendering.append("\n");
        }

        while (verticalPosition > 0) {
            rendering.append("\n");

            ++firstCharacterPosition;
            --lastCharacterPosition;
            --verticalPosition;

            renderRow(rendering, verticalPosition, firstCharacterPosition, lastCharacterPosition);
        }

        return rendering.toString();
    }

    private void renderRow(
            StringBuilder rendering,
            int verticalPosition,
            int firstCharacterPosition,
            int lastCharacterPosition
    ) {
        int horizontalPosition = 0;
        while (horizontalPosition <= lastCharacterPosition) {
            if (horizontalPosition == firstCharacterPosition || horizontalPosition == lastCharacterPosition) {
                rendering.append(alphabet.characterAt(verticalPosition));
            } else {
                rendering.append(" ");
            }

            ++horizontalPosition;
        }
    }
}
