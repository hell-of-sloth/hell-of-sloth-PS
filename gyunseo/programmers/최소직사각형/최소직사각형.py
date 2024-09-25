import unittest


def solution(sizes):
    answer = 0
    widths = []
    heights = []
    for width, height in sizes:
        if width > height:
            width, height = height, width

        widths.append(width)
        heights.append(height)

    max_width, max_height = max(widths), max(heights)

    answer = max_width * max_height
    return answer


class SolutionTest(unittest.TestCase):
    def test_solution(self):
        self.assertEqual(solution([[60, 50], [30, 70], [60, 30], [80, 40]]), 4000)
        self.assertEqual(solution([[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]), 120)
        self.assertEqual(solution([[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]), 133)


if __name__ == "__main__":
    unittest.main()
