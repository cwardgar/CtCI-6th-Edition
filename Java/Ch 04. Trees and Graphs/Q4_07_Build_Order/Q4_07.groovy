package Q4_07_Build_Order

/**
 Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
 projects, where the second project is dependent on the first project). All of a project's dependencies
 must be built before the project is. Find a build order that will allow the projects to be built. If there
 is no valid build order, return an error.
 EXAMPLE
 Input:
 projects: a, b, c, d, e, f
 dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 Output: f, e, a, b, d, c
 *
 * @author cwardgar
 * @since 2017-10-13
 */

class Graph<E> {
    List<Node<E>> nodes = []
    
    Node<E> add(E value) {
        Node<E> node = new Node<>(value: value)
        nodes << node
        node
    }
    
    @Override
    String toString() {
        StringBuilder sb = new StringBuilder()
        nodes.each {
            sb << it << '\n'
        }
        sb.deleteCharAt(sb.length() - 1).toString()
    }
    
    static class Node<E> {
        E value
        List<Node<E>> neighbors = []
        State state = State.NOT_BUILT
        
        @Override
        String toString() {
            StringBuilder sb = new StringBuilder()
            sb << value << ": "
            neighbors.each {
                sb << it.value << ", "
            }
            sb.delete(sb.length() - 2, sb.length()).toString()
        }
        
        enum State {
            NOT_BUILT, BUILDING, BUILT
        }
    }
}

static <T> List<T> findBuildOrder(List<T> projects, List<List<T>> dependencies) {
    Graph<T> graph = makeProjectDependencyGraph(projects, dependencies)
    List<T> buildOrder = []
    
    for (Graph.Node<T> projectNode : graph.nodes) {
        List<T> builtProjects = buildProject(projectNode)
        if (builtProjects == null) {
            return null
        } else {
            buildOrder.addAll builtProjects
        }
    }
    
    buildOrder
}

static <T> Graph<T> makeProjectDependencyGraph(List<T> projects, List<List<T>> dependencies) {
    Graph<T> graph = new Graph<>()
    Map<T, Node<T>> nodeMap = new HashMap<>()
    
    projects.each { T project ->
        Graph.Node<T> node = graph.add(project)
        nodeMap[project] = node
    }
    
    dependencies.each { List<T> dependency ->
        nodeMap[dependency[1]].neighbors << nodeMap[dependency[0]]
    }
    
    graph
}

// Returns the list of projects that were built, or null if a circular dependency was detected.
static <T> List<T> buildProject(Graph.Node<T> projectNode) {
    if (projectNode.state == Graph.Node.State.BUILDING) {
        // We've detected a cycle!
        return null
    } else if (projectNode.state == Graph.Node.State.BUILT) {
        return []
    }
    
    projectNode.state = Graph.Node.State.BUILDING
    List<T> builtProjects = []
    
    for (Graph.Node<T> dependency : projectNode.neighbors) {
        List<T> builtDependencies = buildProject(dependency)
        if (builtDependencies == null) {
            return null
        } else {
            builtProjects.addAll builtDependencies
        }
    }
    
    // All of projectNode's dependencies have been built, so we can now build projectNode itself.
    projectNode.state = Graph.Node.State.BUILT
    return builtProjects << projectNode.value
}

///////////////////////////////////

//def projects = [ "a", "b", "c", "d", "e", "f" ]
//def dependencies = [ ["a", "d"], ["f", "b"], ["b", "d"], ["f", "a"], ["d", "c"] ]

def projects = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j"]
def dependencies = [
    ["a", "b"],
    ["b", "c"],
    ["a", "c"],
    ["d", "e"],
    ["b", "d"],
    ["e", "f"],
    ["a", "f"],
    ["h", "i"],
    ["h", "j"],
    ["i", "j"],
    ["g", "j"]
]

List<String> buildOrder = findBuildOrder(projects, dependencies)
println buildOrder
