//
import {useRightSelectNodeId} from "../../Pinia/RightSelectNodeId";
import {AddNote} from "./AddNote";

export const onRightNFAddNote = () => {
    const selectId = useRightSelectNodeId().GetSelectNodeId();
    AddNote(selectId).then();
}